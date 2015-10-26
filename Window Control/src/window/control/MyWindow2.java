package window.control;

import java.awt.*;
import java.applet.*;

public class MyWindow2 extends Applet  {
Frame window;
Button open,close;

public void init()
{
open=new Button("Open Window");
add(open);
close=new Button("Close Window");
add(close);
window=new Frame("A Pop Up Window");
window.resize(150,150);
MenuBar mbar=new MenuBar();
Menu myMenu=new Menu("File");
myMenu.add(new MenuItem("New"));
myMenu.add(new MenuItem("Open"));
myMenu.add(new MenuItem("Save"));
mbar.add(myMenu);
window.setMenuBar(mbar);
}
public boolean action(Event evt,Object arg)
{
if(evt.target instanceof Button)
{
String label=(String)arg;
if(label.equals("Open Window"))
{
if(!window.isShowing())
window.show();
}
else
{
if(window.isShowing())
window.hide();
}
return true;
}
else
return false;
}
}
