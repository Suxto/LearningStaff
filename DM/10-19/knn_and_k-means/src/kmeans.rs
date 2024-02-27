use super::utils::item::*;
use std::collections::BTreeMap;

fn train(data: &Vec<Item>, k: usize) -> Vec<Item> {
    let mut model: Vec<Item> = data.iter().take(k).cloned().collect();
    let dim = data.first().unwrap().attr.len();
    loop {
        let pre_model = model.clone();
        let mut cnt: Vec<(Vec<f64>, usize)> = vec![(vec![0f64; dim], 0); k];
        for i in data.iter() {
            let mut tmp: Vec<(f64, usize)> = vec![];
            for (idx, m) in model.iter().enumerate() {
                tmp.push((i.dist(m), idx));
            }
            // println!("{:?}", tmp);
            let (_, idx) = tmp
                .iter()
                .min_by(|a, b| a.0.partial_cmp(&b.0).unwrap())
                .unwrap();
            cnt[*idx]
                .0
                .iter_mut()
                .zip(i.attr.iter())
                .for_each(|(a, b)| *a += b);
            cnt[*idx].1 += 1;
        }
        cnt.iter().zip(model.iter_mut()).for_each(|(a, b)| {
            b.attr
                .iter_mut()
                .zip(a.0.iter())
                .for_each(|(x, y)| *x = y / a.1 as f64)
        });

        if model.iter().zip(pre_model.iter()).fold(0.0, |acc, (a, b)| {
            acc + a
                .attr
                .iter()
                .zip(b.attr.iter())
                .fold(0.0, |acc, (x, y)| acc + (x - y).abs())
        }) < 1e-10
        {
            break;
        }
    }

    let mut cnt: Vec<BTreeMap<String, usize>> = vec![BTreeMap::new(); model.len()];
    for i in data.iter() {
        let mut tmp: Vec<(f64, usize)> = vec![];
        for (idx, m) in model.iter().enumerate() {
            tmp.push((i.dist(m), idx));
        }
        let (_, idx) = tmp
            .iter()
            .min_by(|a, b| a.0.partial_cmp(&b.0).unwrap())
            .unwrap()
            .clone();
        *cnt[idx].entry(i.label.clone()).or_insert(0) += 1;
    }
    model
        .iter_mut()
        .zip(cnt.iter())
        .for_each(|(a, b)| a.label = b.iter().max_by(|a, b| a.1.cmp(&b.1)).unwrap().0.clone());

    model
}

fn test(model: &Vec<Item>, data: &Vec<Item>) -> f64 {
    let mut cnt = 0;
    for i in data.iter() {
        let mut tmp: Vec<(f64, usize)> = vec![];
        for (idx, m) in model.iter().enumerate() {
            tmp.push((i.dist(m), idx));
        }
        let (_, idx) = tmp
            .iter()
            .min_by(|a, b| a.0.partial_cmp(&b.0).unwrap())
            .unwrap()
            .clone();
        if model[idx].label == i.label {
            cnt += 1;
        }
    }
    cnt as f64 / data.len() as f64
}

pub fn main() {
    let (tr, te) = read_file("data/fisheriris.csv");
    let model = train(&tr, 3);
    println!("Accuracy = {}", test(&model, &te));
}
