pub mod item {
    use rand::Rng;
    use std::fs::File;
    use std::io::{BufRead, BufReader};

    #[derive(Clone)]
    pub struct Item {
        pub attr: Vec<f64>,
        pub label: String,
    }

    impl Item {
        pub fn dist(&self, that: &Item) -> f64 {
            that.attr
                .iter()
                .zip(self.attr.iter())
                .fold(0f64, |acc, (a, b)| acc + (a - b).powi(2))
                .sqrt()
        }
    }

    pub fn read_file(path: &str) -> (Vec<Item>, Vec<Item>) {
        const HOLDOUT: f64 = 0.70;
        let mut rng = rand::thread_rng();
        let mut train: Vec<Item> = vec![];
        let mut test: Vec<Item> = vec![];
        let file = File::open(path).unwrap();
        let mut reader = BufReader::new(file);
        let mut first_row = String::new();
        reader
            .read_line(&mut first_row)
            .expect("Unable to read file");
        let dim = first_row
            .as_bytes()
            .iter()
            .fold(0, |acc, x| if *x == b',' { acc + 1 } else { acc });

        println!("Each item got a {dim} dimension vector");
        for r in reader.lines() {
            let s = r.unwrap();
            let strs = s.split(',');
            let item = Item {
                attr: strs
                    .clone()
                    .into_iter()
                    .take(dim - 1)
                    .map(|x| x.parse().unwrap())
                    .collect(),
                label: strs.last().clone().unwrap().to_string(),
            };
            match rng.gen_range(0.0..=1.0) {
                #[allow(illegal_floating_point_literal_pattern)]
                0.0..=HOLDOUT => train.push(item),
                _ => test.push(item),
            };
        }
        println!(
            "There are {} items in train set, and {} items in test set",
            train.len(),
            test.len()
        );
        (train, test)
    }
}
