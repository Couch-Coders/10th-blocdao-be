package com.blocdao.project.config;

import com.blocdao.project.BlocdaoApplicationTests;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JasyptConfigTest extends BlocdaoApplicationTests {

    @Test
    public void jasyptTest() {

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        String plainText = "8260a9e63e07b4f965db208d88cf963122f174f382b70ac23b291d06ece6fc47";
        config.setPassword("blocbe!");
        config.setPoolSize("1");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setStringOutputType("base64");
        config.setKeyObtentionIterations("1000");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        encryptor.setConfig(config);

        String encryptText = encryptor.encrypt(plainText);
        System.out.println(encryptText);
        String decryptText = encryptor.decrypt(encryptText);
        System.out.println(decryptText);
        assertThat(plainText).isEqualTo(decryptText);
    }
}