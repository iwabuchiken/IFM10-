package ifm10.listeners;

import ifm10.utils.Methods;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class CustomOnItemLongClickListener implements OnItemLongClickListener {

	Activity actv;
	static Vibrator vib;

	//
	static Methods.ItemTags itemTag = null;
	
	//
	ArrayAdapter<String> dirListAdapter;	// Used in => case dir_list_move_files
	
	//
	Dialog dlg;	// Used in => case dir_list_move_files
	
	//
	List<String> fileNameList;	// Used in => case dir_list_move_files
	
	/****************************************
	 * Constructor
	 ****************************************/
	public CustomOnItemLongClickListener(Activity actv) {
		// 
		this.actv = actv;
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	/*----------------------------
	 * Used in => case dir_list_move_files
		----------------------------*/
	public CustomOnItemLongClickListener(Activity actv,
			Dialog dlg, ArrayAdapter<String> dirListAdapter, List<String> fileNameList) {
		// 
		this.actv = actv;
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		this.dlg = dlg;
		this.fileNameList = fileNameList;
		this.dirListAdapter = dirListAdapter;
		
	}//public CustomOnItemLongClickListener

	/****************************************
	 * Methods
	 ****************************************/
//	@Override
	public boolean onItemLongClick(
										AdapterView<?> parent, View v,
										int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get item
		 * 2. Get tag
		 * 3. Vibrate
		 * 
		 * 4. Is the tag null?
		 * 
		 * 5. If no, the switch
			----------------------------*/
		
		//
		Methods.ListTags tag = (Methods.ListTags) parent.getTag();
		
		// Log
		Log.d("CustomOnItemLongClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Tag => " + tag.name());

		//
//		vib.vibrate(400);
		vib.vibrate(40);
		
		switch (tag) {
		
		case actv_main_lv:
			
			/*----------------------------
			 * 0. Get folder name
				----------------------------*/
			String folderName = (String) parent.getItemAtPosition(position);

			// Log
			Log.d("CustomOnItemLongClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "folderName: " + folderName);
			
			/*----------------------------
			 * 5.1.1. Is a directory?
				----------------------------*/
			String curPath = Methods.get_currentPath_from_prefs(actv);
			
			File targetFile = new File(curPath, folderName);
			
			if (targetFile.exists() && targetFile.isFile()) {
				// debug
				Toast.makeText(actv, "�t�@�C��", 2000).show();
				
	//			return false;
				return true;		//=> "false" => Then, onClick process starts
				
			}//if (targetFile.exists() && targetFile.isFile())
			
			
			/*----------------------------
			 * 5.1.2. If yes, call a method
				----------------------------*/
			Methods.dlg_removeFolder(actv, folderName);
						
			break;
		
		}//switch (tag)
		
		
//		return false;
		return true;
		
	}//public boolean onItemLongClick()

}
