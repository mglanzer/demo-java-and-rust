use serde::{Serialize, Deserialize};
use std::error::Error;

mod java_api;

#[derive(Serialize, Deserialize, Debug)]
struct Data {
    number: usize,
}

#[derive(Serialize, Deserialize)]
#[serde(tag = "cmd", content = "data")]
enum Message {
    Stats(Vec<Data>)
}

#[derive(Serialize, Deserialize)]
struct StatsResponse {
    total: usize
}

// This method is called from Java
pub fn execute(input: String) -> String {
    match execute_safe(input) {
        Ok(response) => response,
        Err(e) => e.to_string(),
    }
}

fn execute_safe(input: String) -> Result<String, Box<dyn Error>> {
    let m: Message = serde_json::from_str(&input)?;
    let result: String;

    match m {
        Message::Stats(v) => {
            let total = v.iter()
                .map(|d| d.number)
                .fold(0, |acc, n| acc + n);

            let response = StatsResponse { total };

            result = serde_json::to_string(&response).unwrap()
        }
    }

    Ok(result)
}
