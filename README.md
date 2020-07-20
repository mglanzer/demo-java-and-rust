Java and Rust
=============

Installation: You'll need to have rust and cargo installed, `cargo build` is executed via gradle task.

Small example of JNI operating with a Rust binary to get a feel for working with the [JNI Crate](https://docs.rs/jni)

Curiosity also led to the inclusion of JMH benchmarks testing mostly JSON deserialization with Jackson and in the Rust code the [serde](https://docs.rs/serde) crate.

Code organization & gradle not given a lot of thought/time, eg: the rust code is just sitting on `/src`.
