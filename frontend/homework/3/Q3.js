const shoes=[{
    type:'sports',
    color:'blue',
    size:'US 8',
    price:140.00
},
{
    type:'casual',
    color:'black',
    size:'US 9',
    price:180.00
}];

const shirts=[{
    type:'formal',
    color:'blue',
    size:'L',
    price:140.00
},
{
    type:'party',
    color:'black',
    size:'M',
    price:190.00
},
{
    type:'casual',
    color:'green',
    size:'XL',
    price:100.00
}];
// A) Create a Warehouse Array
let warehouse=[...shoes,...shirts]
console.log("Warehouse: ",warehouse)

// B) Total worth
let totalAmt=0;
warehouse.forEach(Element=>{
    totalAmt+=Element.price
})
console.log("Total: ",totalAmt)

// C) Descending sort
warehouse.sort((a, b) => b.price - a.price);
console.log("Reverse Sorted: ",warehouse)

// D) Products which are blue in color
blue=[]
warehouse.forEach(Element=>{
    if(Element.color=='blue'){
        blue.push(Element)
    }
})
console.log("Blue Objects: ",blue)