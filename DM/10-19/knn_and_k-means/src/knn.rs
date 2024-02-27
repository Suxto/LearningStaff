use std::collections::BTreeMap;

use super::utils::item::*;

fn calc(train: &Vec<Item>, test: &Vec<Item>, k: usize) -> f64 {
    let mut cnt = 0;
    for i in test {
        let mut tmp: Vec<(f64, String)> = vec![];
        train
            .iter()
            .for_each(|x| tmp.push((i.dist(x), x.label.clone())));
        tmp.sort_by(|a, b| a.0.partial_cmp(&b.0).unwrap());
        let mut nums: BTreeMap<String, i32> = BTreeMap::new();
        tmp.iter()
            .take(k)
            .for_each(|x| *nums.entry(x.1.clone()).or_insert(0) += 1);
        // println!("{:?}", nums);
        let label = nums.iter().max_by(|a, b| a.1.cmp(&b.1)).unwrap().0;
        if *label == i.label {
            cnt += 1;
        }
    }
    cnt as f64 / test.len() as f64
}

pub fn main() {
    let (train, test) = read_file("data/fisheriris.csv");
    (1..test.len() / 3).for_each(|k| println!("K = {k}, Accuracy = {}", calc(&train, &test, k)));
}
