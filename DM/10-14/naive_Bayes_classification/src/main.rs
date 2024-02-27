use std::fmt::{Debug, Formatter};
use std::{
    collections::HashMap,
    fs::File,
    io::{BufRead, BufReader},
};

type DiscreteMap = HashMap<String, Vec<i32>>;

fn main() {
    let data_set = "adult";
    let (mut attrs, mut mark) = read_names(data_set);
    train(data_set, &mut attrs, &mut mark);
    let accu = test(data_set, &attrs, &mark);
    println!("The accuracy {accu}");
}

fn test(name: &str, attrs: &Vec<Attr>, mark: &Mark) -> f64 {
    let path = format!("data/{}.test", name);
    let file = File::open(path).unwrap();
    let reader = BufReader::new(file);

    let mark_num = mark.mark_vals.len();
    let mut cnt = vec![1.0; mark_num];
    let num = mark.mark_vals.iter().fold(0, |acc, (_, (idx, num))| {
        cnt[*idx] = *num as f64;
        acc + num
    });
    // cnt.iter_mut().for_each(|x| *x /= num as f64);
    let mut all = 0;
    let mut correct = 0;
    for line in reader.lines() {
        all += 1;
        let str = line.unwrap();
        if str.is_empty() || str.contains("?") {
            continue;
        }
        let len = str.len();
        let str = str[..len - 1].to_string();
        let mut strs: Vec<_> = str.split(',').into_iter().map(|x| x.trim()).collect();
        let class_idx = mark.mark_vals.get(strs.pop().unwrap()).unwrap().0;
        let mut cnt_now = cnt.clone();
        (0..mark_num).for_each(|i| {
            cnt_now[i] /= num as f64;
            for (idx, x) in strs.iter().enumerate() {
                match &attrs[idx].attr_type {
                    AttrType::Continuous => (), // todo!("Continuous values not supported!"),
                    AttrType::Discrete(map) => {
                        cnt_now[i] *= map.get(*x).unwrap()[i] as f64 / cnt[i];
                    }
                }
            }
        });
        let max_idx = cnt_now
            .iter()
            .enumerate()
            .fold(0, |cur, (idx, x)| if cnt_now[cur] < *x { idx } else { cur });
        if max_idx == class_idx {
            correct += 1;
        }
    }
    return correct as f64 / all as f64;
}

fn train(name: &str, attrs: &mut Vec<Attr>, mark: &mut Mark) {
    let path = format!("data/{}.data", name);
    let file = File::open(path).unwrap();
    let reader = BufReader::new(file);
    for line in reader.lines() {
        let str = line.unwrap();
        if str.is_empty() || str.contains("?") {
            continue;
        }
        let mut strs: Vec<_> = str.split(',').into_iter().map(|x| x.trim()).collect();
        let mark_idx = mark.mark_vals.get_mut(strs.pop().unwrap()).unwrap();
        mark_idx.1 += 1;
        let mark_idx = mark_idx.0;
        strs.iter()
            .enumerate()
            .for_each(|(idx, x)| match &mut attrs[idx].attr_type {
                AttrType::Continuous => (),
                //todo!("Continuous values not supported!"),
                AttrType::Discrete(map) => map.get_mut(*x).unwrap()[mark_idx] += 1,
            });
    }
}

fn read_names(name: &str) -> (Vec<Attr>, Mark) {
    let path = format!("data/{}.names", name);
    let file = File::open(path).unwrap();
    let reader = BufReader::new(file);

    let mut mark_num = 0;
    let mut attrs: Vec<Attr> = vec![];
    let mut mark: Mark = Mark {
        mark_name: "mark".to_string(),
        mark_vals: HashMap::new(),
    };
    for line in reader.lines() {
        let now = line.unwrap();
        if now.is_empty() || now.starts_with('|') {
            continue;
        }
        let len = now.len();
        let now = now[..len - 1].to_string();
        if mark_num == 0 {
            now.split(',').into_iter().for_each(|x| {
                let idx = mark.mark_vals.len();
                mark.mark_vals.insert(x.trim().to_owned(), (idx, 0));
            });
            mark_num = mark.mark_vals.len();
        } else {
            let strs: Vec<_> = now.split(':').collect();
            let name = strs[0].trim().to_string();
            if strs[1].trim() == "continuous" {
                attrs.push(Attr {
                    attr_name: name,
                    attr_type: AttrType::Continuous,
                });
                continue;
            }
            let mut map: DiscreteMap = HashMap::new();
            strs[1].split(',').into_iter().for_each(|x| {
                map.insert(x.trim().to_owned(), vec![0; mark_num]);
            });
            attrs.push(Attr {
                attr_name: name,
                attr_type: AttrType::Discrete(map),
            });
        }
    }
    println!("{} mark type read:", mark.mark_vals.len());
    mark.mark_vals.iter().for_each(|x| print!("{} ", x.0));
    println!();
    println!("{} attribute read", attrs.len());
    attrs.iter().for_each(|x| println!("{:?}", x));
    return (attrs, mark);
}

#[derive(Debug)]
struct Attr {
    attr_name: String,
    attr_type: AttrType,
}

enum AttrType {
    Continuous,
    Discrete(DiscreteMap),
}

impl Debug for AttrType {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        match self {
            AttrType::Continuous => write!(f, "continuous"),
            AttrType::Discrete(map) => {
                let mut s = String::new();
                map.iter().for_each(|x| {
                    s.push_str(x.0);
                    s.push(',');
                });
                write!(f, "Discrete: {}", s)
            }
        }
    }
}

struct Mark {
    mark_name: String,
    mark_vals: HashMap<String, (usize, i32)>, //(idx,cnt)
}
