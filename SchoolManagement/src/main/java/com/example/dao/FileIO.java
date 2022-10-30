package com.example.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// I want this FileIO class to be general purpose, i.e. be able to take in any type of data
// so we use a Generic: <T> means General Type
public class FileIO<T> {

	private String filename;
	
	public FileIO(String filename) {
		this.filename = filename;
	}
	
	public T readObject() {
		T ret;
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			ret = (T) ois.readObject();
			return ret;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("No one has written to the file yet");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	// This is how you write to a file:
	// Open an output stream, take the object you want to write, pass it to the stream, and close the stream
	public void writeObject(T object) {
		// This is just how this gets set up - don't worry about why
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(object);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
