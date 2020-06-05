package Database;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * PBKDF2(Password-Based Key Derivation Function 2) based password hashing class
 */
public class HashPassword {

  /**
   * generate hashed password
   *
   * @param password actual password
   * @return hashed password
   */
  public static String generatePasswordHash(String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    int iterations = 1000;
    char[] chars = password.toCharArray();
    byte[] salt = getSalt();

    PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hash = skf.generateSecret(spec).getEncoded();
    return iterations + ":" + toHex(salt) + ":" + toHex(hash);
  }

  /**
   * To get salt
   *
   * @return byte data type of salt
   */
  private static byte[] getSalt() throws NoSuchAlgorithmException {
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt;
  }

  /**
   * to Hexadecimal converter
   *
   * @param array array of byte data type
   * @return Hexadecimal form of the given array
   */
  private static String toHex(byte[] array) {
    BigInteger bi = new BigInteger(1, array);
    String hex = bi.toString(16);
    int paddingLength = (array.length * 2) - hex.length();
    if (paddingLength > 0) {
      return String.format("%0" + paddingLength + "d", 0) + hex;
    } else {
      return hex;
    }
  }

  /**
   * To check the validation of the password
   *
   * @param originalPassword user password
   * @param storedPassword stored password
   * @return Boolean value
   */
  public static boolean validatePassword(String originalPassword, String storedPassword)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    if (originalPassword.equals(storedPassword)) {
      return true;
    } else {
      String[] parts = storedPassword.split(":");
      int iterations = Integer.parseInt(parts[0]);
      byte[] salt = fromHex(parts[1]);
      byte[] hash = fromHex(parts[2]);

      PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations,
          hash.length * 8);
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      byte[] testHash = skf.generateSecret(spec).getEncoded();

      int diff = hash.length ^ testHash.length;
      for (int i = 0; i < hash.length && i < testHash.length; i++) {
        diff |= hash[i] ^ testHash[i];
      }
      return diff == 0;
    }
  }

  /**
   * Hexadecimal to byte converter
   *
   * @param hex hexadecimal
   * @return array of type byte
   */
  private static byte[] fromHex(String hex) {
    byte[] bytes = new byte[hex.length() / 2];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return bytes;
  }

}