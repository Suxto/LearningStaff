use std::io::{Read, stdin, Write};
use std::net::{TcpListener, TcpStream};
use std::thread;

const ADDR: &str = "localhost";
const PORT: &str = "2333";

fn main() {
    let listener = TcpListener::bind(format!("{ADDR}:{PORT}")).expect("Failed to bind address");
    println!("Server running at {}:{}", ADDR, PORT);

    for stream in listener.incoming() {
        let stream = stream.expect("Failed to establish connection");
        thread::spawn(move || {
            handle_connection(stream);
        });
    }
}

fn handle_connection(mut stream: TcpStream) {
    let mut buffer = [0; 1024];
    stream
        .read(&mut buffer)
        .expect("Failed to read data from stream");

    // Parse HTTP request headers
    let request = String::from_utf8_lossy(&buffer[..]);
    println!("New connection: {request}");
    let response = "Server connected";
    stream
        .write(response.as_bytes())
        .expect("Failed to write response to stream");
    loop {
        let mut message = [0; 1024];
        let result = stream.read(&mut message);
        match result {
            Ok(0) | Err(_) => break,
            Ok(n) => {
                // Process received WebSocket message
                let message = &message[..n];
                println!("Received message: {}", String::from_utf8_lossy(message));

                println!("Type any thing need to sand");
                let mut input = String::new();
                stdin().read_line(&mut input).expect("Unable to read from stdio");
                // Echo the message back to the client
                stream
                    .write(input.as_bytes())
                    .expect("Failed to write message to stream");
            }
        }
    }
}
