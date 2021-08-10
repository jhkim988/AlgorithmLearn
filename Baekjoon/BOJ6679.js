const transf = (num, rule) => {
    let result = [];
    do{
        let re = num%rule;
        result.push(re)
        num = (num-re)/rule;
    } while(num > rule){
        result.push(num)
    }
    return result;
}

const sum = (li) => {
    let result = 0
    for(value of li){
        result += value;
    };
    return result;
}

for(let k=1000; k<10000; k++){
    let sum10 = sum(transf(k,10));
    let sum12 = sum(transf(k,12));
    let sum16 = sum(transf(k,16));
    if(sum10==sum12 && sum12==sum16){
        console.log(k);
    };
};

