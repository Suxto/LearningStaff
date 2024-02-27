use std::net::TcpStream;
use std::io::{Read, stdin, Write};

const ADDR: &str = "localhost";
const PORT: &str = "2333";

fn main() {
    let mut stream = TcpStream::connect(format!("{ADDR}:{PORT}")).expect("Failed to connect to server");

    let request = "Request connect";
    stream.write(request.as_bytes()).expect("Failed to send request to server");

    let mut buffer = [0; 1024];
    stream.read(&mut buffer).expect("Failed to read response from server");

    // Parse HTTP response
    let response = String::from_utf8_lossy(&buffer[..]);
    println!("{response}");
    loop {
        let mut input = String::new();
        stdin().read_line(&mut input).expect("Unable to read from stdio");

        stream.write(input.as_bytes()).expect("Failed to send message to server");

        let mut response = [0; 1024];
        let result = stream.read(&mut response);
        match result {
            Ok(0) | Err(_) => break,
            Ok(n) => {
                // Process received WebSocket message
                let message = &response[..n];
                println!("Received message: {}", String::from_utf8_lossy(message));
            }
        }
    }
}