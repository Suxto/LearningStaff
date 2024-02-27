use std::io::{stdin, stdout, Write};

mod kmeans;
mod knn;
mod utils;

fn main() {
    println!("input 1 for knn and 2 for k-means: ");
    stdout().flush().expect("Output error");
    let mut read = String::new();
    stdin().read_line(&mut read).expect("Invalid input");
    // println!("{read}");
    match read.trim().parse::<i32>() {
        Ok(x) => {
            if x == 1 {
                knn::main();
            } else if x == 2 {
                kmeans::main();
            } else {
                println!("Invalid input");
            }
        }
        _ => println!("Invalid input"),
    };
}
