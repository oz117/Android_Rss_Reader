package com.epitech.my_test_http.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Permet la lecture/ecriture dans un objet à partir
 * d'un fichier dans le dossier /data d'une application
 *
 * Created by zero on 28/04/15.
 */
public class                    WriteObjectFile {
	private Context             _parent;
	private FileInputStream     _fileIn;
	private FileOutputStream    _fileOut;
	private ObjectInputStream   _objectIn;
	private ObjectOutputStream  _objectOut;
	private Object              _outputObject;

	public WriteObjectFile(Context c){
		_parent = c;
	}

	public Object               readObject(String fileName){
		try {
			_fileIn = _parent.getApplicationContext().openFileInput(fileName);
			_objectIn = new ObjectInputStream(_fileIn);
			_outputObject = _objectIn.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (_objectIn != null) {
				try {
					_objectIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return _outputObject;
	}

	public void                 writeObject(Object inputObject, String fileName){
		try {
			_fileOut = _parent.openFileOutput(fileName, Context.MODE_PRIVATE);
			_objectOut = new ObjectOutputStream(_fileOut);
			_objectOut.writeObject(inputObject);
			_fileOut.getFD().sync();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (_objectOut != null) {
				try {
					_objectOut.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
	}
}