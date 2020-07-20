package me.mpg;

public class Native {
    // This declares that the static `hello` method will be provided
    // a native library.
    public static native String execute(String json);

    static {
        // This actually loads the shared object that we'll be creating.
        // The actual location of the .so or .dll may differ based on your
        // platform.
        System.loadLibrary("execute");
    }

    // The rest is just regular ol' Java!
    public static void main(String[] args) {
        String output = Native.execute("josh");
        System.out.println(output);
    }
}
