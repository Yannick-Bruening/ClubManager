package de.toxicpointer.clubmanager.utils.serialization;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class TypedDeserializer<T extends Serializable> {

  public T deserialize(final File file) {
    try (final FileInputStream fileInputStream = new FileInputStream(file);
         final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
         final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {
      final Object readObject = objectInputStream.readObject();

      return (T) readObject;
    } catch (final IOException | ClassNotFoundException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  // https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
  public T[] deserializeMany(final File file) {
    System.out.println(file.getAbsolutePath());
    try (final FileInputStream fileInputStream = new FileInputStream(file);
         final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
      final ArrayList<T> objects = new ArrayList<>();

      while (bufferedInputStream.available() > 0) {
        final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);

        final Object readObject = objectInputStream.readObject();
        objects.add((T) readObject);

        objectInputStream.close();
      }

      return (T[]) objects.toArray();
    } catch (final IOException | ClassNotFoundException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}
