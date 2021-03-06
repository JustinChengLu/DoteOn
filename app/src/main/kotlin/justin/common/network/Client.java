package justin.common.network;

import android.annotation.SuppressLint;
import android.content.Context;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import justin.common.utils.ACache;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * @author justin on 2017/04/13 14:57
 * @version V1.0
 */
public class Client {

    private static OkHttpClient okHttpClient = null;
    private static ACache aCache;
    public static void init(Context context){
        try{
            X509TrustManager xtm = new X509TrustManager() {
                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            Cache cache = new Cache(context.getCacheDir(),1024*1024*30);

            okHttpClient =  new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .cache(cache)
                    .sslSocketFactory(sslSocketFactory, xtm)
                    .hostnameVerifier((hostname, session) -> true).build();
            aCache = ACache.get(context,"okhttp");
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public static OkHttpClient get() {
        return okHttpClient;
    }


    public static ACache getCache(){
        return aCache;
    }
}
