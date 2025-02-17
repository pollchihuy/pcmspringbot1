package com.example.pcmspringbot1.security;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESLightEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class Crypto {

//    private static final String defaultKey = "12fc5e5b5abe431b94870823a154e511d2e3419f47c455a9659d89268776a3eb";
//    private static final String defaultKey = "7bf29d8a22a03f43baf953f5c5e4cb264dbf2db3651da21d28daee478a189cce";

    //28836c299e3515fb1e2b59cdd91fec54
//    Paul@123
//    28836c299e3515fb1e2b59cdd91fec54
//    2d38e01c52c0d9c475dac174eff7cea6464778ec35f3d78eba5c1421d684a3e6
//    private static final String defaultKey = "2d38e01c52c0d9c475dac174eff7cea6464778ec35f3d78eba5c1421d684a3e6";

    //    Paul@123
//    f96dab321e389b0ffb538bffb26ccb3d
//    65c921be01467876e107cf73bd097f38f79b5c218f4da1b52931dd3f0cc79992
    private static final String defaultKey = "349bcade4ca4f10ac0a908a8d2c764a3ee53095f69fbc7e29c95f8f9813fc6dc";

    //942cb1c2439758f34fed2b971fc1c3d6
//    Paul@123
//    09e68b20bcb40f00a09464c5b02de257
//    4c7304fbb592200766424b0334d359257a45cbb8737dee86d458be14ccac4cb4
//    private static final String defaultKey = "4c7304fbb592200766424b0334d359257a45cbb8737dee86d458be14ccac4cb4";

    /** 7e52334e09314807e67ed3882359adf0 --> e07169ee08959f7ba1118af6e607fcb3
     * U2FsdGVkX1/CwKWoBplvmTWuOfWkYAp+ICTG71sKZO0=
     * */
//    private static final String defaultKey = "7e52334e09314807e67ed3882359adf0";
    public static String performEncrypt(String keyText, String plainText) {
        try{
            byte[] key = Hex.decode(keyText.getBytes());
            byte[] ptBytes = plainText.getBytes();
            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESLightEngine()));
            cipher.init(true, new KeyParameter(key));
            byte[] rv = new byte[cipher.getOutputSize(ptBytes.length)];
            int oLen = cipher.processBytes(ptBytes, 0, ptBytes.length, rv, 0);
            cipher.doFinal(rv, oLen);
            return new String(Hex.encode(rv));
        } catch(Exception e) {
            return "Error";
        }
    }

    public static String performEncrypt(String cryptoText) {
        return performEncrypt(defaultKey, cryptoText);
    }

    public static String performDecrypt(String keyText, String cryptoText) {
        try {
            byte[] key = Hex.decode(keyText.getBytes());
            byte[] cipherText = Hex.decode(cryptoText.getBytes());
            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESLightEngine()));
            cipher.init(false, new KeyParameter(key));
            byte[] rv = new byte[cipher.getOutputSize(cipherText.length)];
            int oLen = cipher.processBytes(cipherText, 0, cipherText.length, rv, 0);
            cipher.doFinal(rv, oLen);
            return new String(rv).trim();
        } catch(Exception e) {
            return "Error";
        }
    }

    public static String performDecrypt(String cryptoText) {
        return performDecrypt(defaultKey, cryptoText);
    }

    public static void main(String[] args) {
//        String strToEncrypt = "dzjccuhbc";//put text to encrypt in here
        String strToEncrypt = "jdbc:sqlserver://host.docker.internal;databaseName=DBPROJECT;schema=batch23;trustServerCertificate=true";//put text to encrypt in here
        System.out.println("Encryption Result : "+performEncrypt(strToEncrypt));

        String strToDecrypt = "64a90dd4d2fa3a326854d37769e5e4eac15e11d98f3a042868db5e83a3c76567da9496df38ef10333a5aa33f14e15dc7e485e50a28862810c9d9afbda03ebe64832f3aabbca944bf34bed3af812fec351b7fc7c9bd26b3d4fdbc31f0297a5ba2";//put text to decrypt in here
//        String decriptionResult = new Crypto().performDecrypt(strToDecrypt);
        System.out.println("Decryption Result : "+performDecrypt(strToDecrypt));
    }
}