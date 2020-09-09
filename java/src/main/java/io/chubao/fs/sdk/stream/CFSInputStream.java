package io.chubao.fs.sdk.stream;

import io.chubao.fs.sdk.exception.CFSEOFException;
import io.chubao.fs.sdk.CFSFile;
import io.chubao.fs.sdk.exception.CFSException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class CFSInputStream extends InputStream {
  private static final Log log = LogFactory.getLog(CFSInputStream.class);
  private CFSFile cfile;

  public CFSInputStream(CFSFile file) {
    this.cfile = file;
  }

  @Override
  public int available() throws IOException {
    return (int)(cfile.getFileSize() - cfile.getPosition());
  }

  @Override
  public int read(byte[] b, int off, int len) throws IOException {
    int size = 0;
    try {
      size = cfile.read(b, off, len);
      //String data =  new String(b, "utf8");
      //log.info("data:" + data);
      return size;
    } catch (CFSEOFException e) {
      return -1;
    } catch (CFSException ex) {
      throw new IOException(ex);
    }
  }

  @Override
  public void close() throws IOException {
    try {
      cfile.close();
    } catch (CFSException ex) {
      throw new IOException(ex);
    }
}

  @Override
  public int read() throws IOException {
    byte buff[] = new byte[1];
    int bread = read(buff, 0, 1);
    if (bread <= 0) { // no content read
      return bread;
    }

    return (buff[0] & 0xFF);
  }

  public void seek(long pos) throws IOException {
    if (pos > cfile.getFileSize()) {
      throw new EOFException("The pos [" + pos + "] is more than file size, " + cfile.getFileSize());
    }

    try {
      cfile.seek(pos);
    } catch (CFSException ex) {
      throw new IOException(ex);
    }
}

  public synchronized long getPos() throws IOException {
    return cfile.getPosition();
  }

  public boolean seekToNewSource(long pos) throws IOException {
    return pos > cfile.getFileSize() ? false : true;
  }
}

