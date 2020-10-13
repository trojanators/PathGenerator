package pathgenerator.util;

/**
 * @attribute PathWeaver 
 */
public class DuplicateGameException extends RuntimeException {
    /**
   *
   */
  private static final long serialVersionUID = 1L;

  public DuplicateGameException(String message) {
      super(message);
    }
  
    public DuplicateGameException(String message, Throwable cause) {
      super(message, cause);
    }
  }