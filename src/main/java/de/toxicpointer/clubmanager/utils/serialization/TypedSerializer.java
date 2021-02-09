package de.toxicpointer.clubmanager.utils.serialization;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class TypedSerializer<T extends Serializable> {

  public void serialize(final T object, final File file, final boolean append) {
    writeObject(object, file, append);
  }

  public void serializeMany(final T[] objects, final File file, final boolean append) {
    Arrays.stream(objects).forEach(t -> writeObject(t, file, append));
  }

  public void serializeMany(final List<T> objects, final File file, final boolean append) {
    if (!append) {
      file.delete();
    }

    objects.forEach(t -> writeObject(t, file, true));
  }

  private void writeObject(final T object, final File file, final boolean append) {
    try (final FileOutputStream fileOutputStream = new FileOutputStream(file, append);
         final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
         final ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {
      objectOutputStream.writeObject(object);
    } catch (final IOException exception) {
      exception.printStackTrace();
    }
  }

}
