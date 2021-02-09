package de.toxicpointer.clubmanager.utils.serialization;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
  public List<T> deserializeMany(final File file) {
    try (final FileInputStream fileInputStream = new FileInputStream(file);
         final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
      final List<T> objects = new ArrayList<>();

      while (bufferedInputStream.available() > 0) {
        final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);

        objects.add((T) objectInputStream.readObject());
      }

      return objects;
    } catch (final IOException | ClassNotFoundException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}
