package Main;

import java.awt.Component;
import java.awt.Frame;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;


public class Utils {

	/*
	 * return the frame to which 'c' belongs
	 */
	public static Frame getWindow(Component c) {
		Frame w = null;
		if (c != null) {
			if (c instanceof Frame)
				w = (Frame) c;
			else
				w = (Frame) SwingUtilities.getWindowAncestor(c);
		}
		return w;
	}

	public static void showErrorMsg(String msg) {
		showErrorMsg(null, msg);
	}

	static void showErrorMsg(Component c, String msg) {
		JOptionPane.showMessageDialog(getWindow(c), msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showCorrectMsg(String msg){
		JOptionPane.showMessageDialog(null, msg, "CORRECT", JOptionPane.INFORMATION_MESSAGE);
	}

	static void quit(Component c) {

		int n = JOptionPane.showOptionDialog(getWindow(c), "Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == 0) {
			System.exit(0);
		}
	}
	
	public static boolean equalsCollection(Collection<Object> coll, Collection<Object> proveedorColeccion2) {
		boolean correcto=true;
		if(coll.size()!=proveedorColeccion2.size()){
			correcto=false;
		}
		else{
			
			Iterator<Object>it1=coll.iterator();
			Iterator<Object>it2=proveedorColeccion2.iterator();
			
			while(it1.hasNext()&&correcto){
				Object prov1=it1.next();
				Object prov2=it2.next();
				if(!prov1.equals(prov2)) {
					correcto=false;
				}
			}
		}
		return correcto;
	}
	
	public static boolean equalsMap(Map<Object, Object> map1, Map<Object, Object> map2) {
		boolean correcto=true;
		if(map1.size()==map2.size()){
			correcto=false;
		}
		else{
			Iterator<Object>it1=map1.keySet().iterator();
			Iterator<Object>it2=map2.keySet().iterator();
			while(it1.hasNext() && correcto) {
				it1.next();
				it2.next();
				if(!it1.equals(it2)|| map1.get(it1).equals(map2.get(it2)))
					correcto=false;
				
			}
			
		}
		return correcto;
	}
	

}

