package org.rkoubsky.mywebserver;

public class MyWebServer {

    private final String host;
    private final int port;
    private final boolean sslEnabled;
    private final boolean http2Enabled;

    public MyWebServer(String host, int port, boolean sslEnabled, boolean http2Enabled) {
        this.host = host;
        this.port = port;
        this.sslEnabled = sslEnabled;
        this.http2Enabled = http2Enabled;
    }

    public MyWebServer(String host, int port, boolean sslEnabled) {
        this(host, port, sslEnabled, false);
    }

    public void start(){
        System.out.printf("Web server started at %s%s:%s, enableHttp2=%s\n",
                          getProtocol(), this.host, port, http2Enabled);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public boolean isHttp2Enabled() {
        return http2Enabled;
    }

    private String getProtocol() {
        return sslEnabled ? "https://" : "http://";
    }
}
