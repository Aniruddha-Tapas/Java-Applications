package database.conversion;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.DatabaseMetaData.*;
import java.lang.String.*;
import java.util.Map.*;

class FieldInfo {

    public String Name;
    public String Type;
    public int Size;
    public int Precision;
    public int Scale;
    public boolean Primary;
    public boolean Unique;
    public boolean AllowNull;

    public String toString() {
        return "[ " + Name + ", " + Type + ", " + Size + ", " + Precision + ", "
                + Scale + ", " + Primary + ", " + Unique + ", " + AllowNull + " ]";
    }
}

public class DataMigration {

    String s2[][];
    int col, p, j1;

    /*String strTypeName[] = {
     "GUID", "BIT", "BYTE", "LONGBINARY", "VARBINARY", "BINARY", "LONGCHAR",
     "CHAR", "CURRENCY", "INTEGER", "COUNTER", "SMALLINT", "REAL", "DOUBLE",
     "DATETIME", "VARCHAR"
     };

     private static String strSQLServerType[] = {
     "UNIQUEIDENTIFIER", "BIT", "TINYINT", "VARBINARY", "VARBINARY", "VARBINARY", "VARBINARY",
     "CHAR", "MONEY", "INT", "INT", "SMALLINT", "REAL", "DECIMAL", "SMALLDATETIME", "VARCHAR"
     };

     private static String strOracleType[] = {
     "UROWID", "CHAR", "NUMBER", "BLOB", "BLOB", "BLOB", "LONG",
     "CHAR", "NUMBER", "NUMBER", "NUMBER", "NUMBER", "NUMBER", "NUMBER",
     "DATE", "VARCHAR2"
     };*/
    LinkedList _fieldList = null;

    public LinkedList getFieldInfoList(String strTable, Connection conn, String strDestDSN, String strDestUser, String strDestPwd) {
        _fieldList = new LinkedList();

        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsIndex = dbmd.getIndexInfo(null, null, strTable, true, false);

            ArrayList arrIndexColumn = new ArrayList();
            ArrayList arrIndexName = new ArrayList();

            while (rsIndex.next()) {
                String str = rsIndex.getString("COLUMN_NAME");
                if (str == null) {
                    str = "";
                }
                arrIndexColumn.add(str);
                str = rsIndex.getString("INDEX_NAME");
                if (str == null) {
                    str = "";
                }
                arrIndexName.add(str);
            }

            rsIndex.close();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + strTable);
            ResultSetMetaData rsmd = rs.getMetaData();
            col = rsmd.getColumnCount();

            String s1[] = new String[col];
            String s2[][] = new String[col][col];
            int j = 0, n = 1, ij = 0, l1 = 1;

            while (rs.next()) {
                for (p = 0; p < col; p++) {
                    s2[j][p] = rs.getString(n);
                    n++;
                }
                n = 1;
                j++;
            }

            for (int i = 1; i < col; i++) {
                FieldInfo info = new FieldInfo();
                info.Name = rsmd.getColumnName(i);
                info.Type = rsmd.getColumnTypeName(i);
                info.Size = rsmd.getColumnDisplaySize(i);
                info.Precision = rsmd.getPrecision(i);
                info.Scale = rsmd.getScale(i);
                info.Primary = false;
                info.Unique = false;
                info.AllowNull = (rsmd.isNullable(i) == ResultSetMetaData.columnNullable);

                for (int k = 0; k < arrIndexColumn.size(); k++) {
                    String s = arrIndexColumn.get(k).toString();
                    if (s.equals(info.Name)) {
                        info.Unique = true;
                        break;
                    }
                }

                _fieldList.add(info);
            }

            String ss = "insert into " + strTable + " values (?,?,?)";

            Connection connDest = DriverManager.getConnection("jdbc:odbc:" + strDestDSN, strDestUser, strDestPwd);
            PreparedStatement p = connDest.prepareStatement(ss);

            for (int p1 = 0; p1 < 3; p1++) {
                p.setString(1, s2[p1][0]);
                p.setString(2, s2[p1][1]);
                p.setString(3, s2[p1][2]);
                if (p.executeUpdate() == 0) {
                    System.out.println("Wrong");
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
            _fieldList.clear();
        }
        System.out.println("ravi");
        return _fieldList;

    }

    public String getFieldDesc(FieldInfo fi, String strToDB) {
        String str = "";

        try {
            String s2 = new String(fi.Name);
            s2 = s2.replace(' ', '_');
            str = s2 + " ";
            String s = fi.Type.trim().toUpperCase();

            if (s.equals("GUID")) {
                if (strToDB.equals("SQLServer")) {
                    str += " UNIQUEIDENTIFIER ";
                } else {
                    str += " UROWID ";
                }
            } else if (s.equals("BIT")) {
                if (strToDB.equals("SQLServer")) {
                    str += " BIT ";
                } else {
                    str += " CHAR(1) ";
                }
            } else if (s.equals("BYTE")) {
                if (strToDB.equals("SQLServer")) {
                    str += " TINYINT ";
                } else {
                    str += " NUMBER ";
                }
            } else if (s.equals("LONGBINARY")) {
                fi.Size = (fi.Size < 0 || fi.Size > 1000) ? 1000 : fi.Size;
                if (strToDB.equals("SQLServer")) {
                    str += " VARBINARY(" + fi.Size + ") ";
                } else {
                    str += " BLOB(" + fi.Size + ") ";
                }
            } else if (s.equals("VARBINARY")) {
                fi.Size = (fi.Size < 0 || fi.Size > 1000) ? 1000 : fi.Size;
                if (strToDB.equals("SQLServer")) {
                    str += " VARBINARY(" + fi.Size + ") ";
                } else {
                    str += " BLOB(" + fi.Size + ") ";
                }
            } else if (s.equals("BINARY")) {
                fi.Size = (fi.Size < 0 || fi.Size > 1000) ? 1000 : fi.Size;
                if (strToDB.equals("SQLServer")) {
                    str += " VARBINARY(" + fi.Size + ") ";
                } else {
                    str += " BLOB(" + fi.Size + ")";
                }
            } else if (s.equals("LONGCHAR")) {
                fi.Size = (fi.Size < 0 || fi.Size > 1000) ? 1000 : fi.Size;
                if (strToDB.equals("SQLServer")) {
                    str += " VARBINARY(" + fi.Size + ") ";
                } else {
                    str += " LONG(" + fi.Size + ")";
                }
            } else if (s.equals("CHAR")) {
                if (strToDB.equals("SQLServer")) {
                    str += " CHAR(1) ";
                } else {
                    str += " CHAR(1) ";
                }
            } else if (s.equals("CURRENCY")) {
                if (strToDB.equals("SQLServer")) {
                    str += " CURRENCY ";
                } else {
                    str += " NUMBER ";
                }
            } else if (s.equals("INTEGER")) {
                if (strToDB.equals("SQLServer")) {
                    str += " INT ";
                } else {
                    str += " NUMBER(10) ";
                }
            } else if (s.equals("COUNTER")) {
                if (strToDB.equals("SQLServer")) {
                    str += " INT ";
                } else {
                    str += " NUMBER(10) ";
                }
            } else if (s.equals("SMALLINT")) {
                if (strToDB.equals("SQLServer")) {
                    str += " INT ";
                } else {
                    str += " NUMBER(5) ";
                }
            } else if (s.equals("REAL")) {
                if (strToDB.equals("SQLServer")) {
                    str += " REAL ";
                } else {
                    str += " NUMBER ";
                }
            } else if (s.equals("DOUBLE")) {
                if (strToDB.equals("SQLServer")) {
                    str += " DECIMAL ";
                } else {
                    str += " NUMBER ";
                }
            } else if (s.equals("DATETIME")) {
                if (strToDB.equals("SQLServer")) {
                    str += " SMALLDATETIME ";
                } else {
                    str += " DATE ";
                }
            } else if (s.equals("VARCHAR")) {
                if (strToDB.equals("SQLServer")) {
                    str += " VARCHAR(" + fi.Size + ") ";
                } else {
                    str += " VARCHAR2(" + fi.Size + ") ";
                }
            } else {
                return "";
            }

            if (fi.Unique) {
                s += " " + "UNIQUE ";
            }
            if (fi.AllowNull) {
                s += " " + "NOT NULL ";
            }
        } catch (Exception ex) {
        }
        return str;
    }

    public String getSQL(String strTable, String strToDB) {
        //String strNewTable = strTable.replace(' ', '_');
        //System.out.println(strNewTable);

        String strSQL = "CREATE TABLE " + strTable + " (";
        for (int i = 0; i < _fieldList.size(); i++) {
            String s = getFieldDesc((FieldInfo) _fieldList.get(i), strToDB);
            if (i == 0) {
                strSQL = strSQL + s;
            } else {
                strSQL = strSQL + ", " + s;
            }
        }
        strSQL = strSQL + ")";

        return strSQL;
    }

    public void migrateStructureAndData(String[] strSrcTable, String strSrcDSN, String strSrcUser, String strSrcPwd,
            String strDestDSN, String strDestUser, String strDestPwd,
            String strToDB, boolean bStructureOnly) throws Exception {
        try {
            ResultSet rsSource = null;
            Statement stmtSrc = null, stmtDest = null;
            Connection connSrc = null, connDest = null;

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connSrc = DriverManager.getConnection("jdbc:odbc:" + strSrcDSN,
                    strSrcUser, strSrcPwd);

            String strDestTable[] = new String[strSrcTable.length];

            for (int i = 0; i < strSrcTable.length; i++) {
                getFieldInfoList(strSrcTable[i], connSrc, strDestDSN, strDestUser,
                        strDestPwd);
                strDestTable[i]
                        = getSQL(strSrcTable[i],
                                strToDB);
                System.out.println(strDestTable[i]);
            }

            connDest
                    = DriverManager.getConnection("jdbc:odbc:" + strDestDSN, strDestUser,
                            strDestPwd);
            stmtDest = connDest.createStatement();

            for (int i = 0; i < strDestTable.length; i++) {
                try {
                    stmtDest.execute(strDestTable[i]);

                    /*if( bStructureOnly == false )
                     {
                     String strInsert = "INSERT INTO " +
                     strSrcTable[i]+"("+s1[1]+","+s1[2]+","+s1[3]+","+s1[4]+","+s1[5]+")"+
                     " VALUES ("+1+","+"ravi"+","+"21"+","+"12000"+","+"1234"+")";
                     //rsSource = stmt.executeQuery("SELECT * FROM " +strSrcTable[i]);

                     stmtDest.execute(strInsert);
                     }*/
                } catch (Exception ex) {
                    /*String s = ex.toString().toLowerCase();
                     if( s.indexOf("there is already an object") >= 0 )
                     System.out.println("'" + strSrcTable[i] + "' already exists");
                     else
                     System.out.println(ex);*/
                }
            }

            //connSrc.close();
            //connDest.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void main(String[] args) {
        DataMigration dm = new DataMigration();
        String[] s = {"authors1", "authors2", "authors3"};
        try {
            dm.migrateStructureAndData(s, "sql", "sa", "", "con1", "", "", "",
                    false);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
