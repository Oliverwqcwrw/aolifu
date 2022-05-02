package com.aolifu.ssl;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;

public class SSLService {
    // 客户端证书路径，用了本地绝对路径，需要修改
    private final static String CLIENT_CERT_FILE = "C:\\Users\\tzx\\Desktop\\client.p12";
    // 客户端证书密码
    private final static String CLIENT_PWD = "131112";
    // 信任库路径，即keytool生成的那个自定义名称的库文件
    private final static String TRUST_STRORE_FILE = "D:\\Java\\jdk1.8.0_131\\jre\\lib\\security\\test.truststore";
    // 信任库密码，即keytool时的密码
    private final static String TRUST_STORE_PWD = "131112";
    private static String readResponseBody(InputStream inputStream) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            StringBuffer sb = new StringBuffer();
            String buff = null;
            while ((buff = br.readLine()) != null) {
                sb.append(buff + "\n");
            }
            return sb.toString();
        } finally {
            inputStream.close();
        }
    }
    public static void httpsCall() throws Exception {
        // 初始化密钥库
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");

        KeyStore keyStore = getKeyStore(CLIENT_CERT_FILE, CLIENT_PWD, "PKCS12");

        keyManagerFactory.init(keyStore, CLIENT_PWD.toCharArray());
        // 初始化信任库
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");

        KeyStore trustkeyStore = getKeyStore(TRUST_STRORE_FILE, TRUST_STORE_PWD, "JKS");
        trustManagerFactory.init(trustkeyStore);
        // 初始化SSL上下文
        SSLContext ctx = SSLContext.getInstance("SSL");
        ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        SSLSocketFactory sf = ctx.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(sf);
        String url = "https://blog.tzx.com";
        URL urlObj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        con.setRequestProperty("Accept-Language", "zh-CN;en-US,en;q=0.5");
        con.setRequestMethod("GET");
        String res = readResponseBody(con.getInputStream());
        System.out.println(res);
    }
    /**
     * 获得KeyStore
     */
    private static KeyStore getKeyStore(String keyStorePath, String password, String type)
            throws Exception {
        FileInputStream is = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance(type);
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }
   public static void main(String[] args) throws Exception {
    httpsCall();
  }
}