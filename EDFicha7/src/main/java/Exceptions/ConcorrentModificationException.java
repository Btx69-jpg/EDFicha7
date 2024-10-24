
package Exceptions;
public class ConcorrentModificationException extends RuntimeException {
    public ConcorrentModificationException(String message) {
        super(message);
    }
}
