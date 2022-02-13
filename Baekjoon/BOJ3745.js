const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

for (let i = 0; i < input.length; i += 2) {
  let n = parseInt(input[i].trim());
  let array = input[i + 1].trim().split(/\s+/g).map(Number);
  const lower_bound = (lo, hi, key, arr) => {
    while (lo + 1 < hi) {
      mid = (lo + hi) >> 1;
      if (arr[mid] < key) lo = mid;
      else hi = mid;
    }
    return hi;
  };

  let dp = [0];
  let ptr = 0;
  for (let j = 0; j < array.length; j++) {
    if (dp[ptr] < array[j]) {
      dp[++ptr] = array[j];
    } else {
      idx = lower_bound(0, ptr, array[j], dp);
      dp[idx] = array[j];
    }
  }
  console.log(ptr);
}
