function tipCalculator(bill){
    let tips=[];
    let finalAmounts = [];

    bill.forEach(element => {
        let tip;
        if(element<50){
            tip = element*0.2;
        }
        else if(50<=element && element<=200){
            tip = element*0.15;
        }
        else if(element>200){
            tip = element*0.1;
        }
        tips.push(tip);
        finalAmounts.push(element + tip); 
    });
    return [tips, finalAmounts]; 
}

const bill = [140, 45, 280];
let [tips, finalAmounts] = tipCalculator(bill);
console.log("Tips: ",tips)
console.log("Final Bill: ",finalAmounts)