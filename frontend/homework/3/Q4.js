const mystr = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24,"city":"london","country":"england"}';

const obj = JSON.parse(mystr, (key, value) => {
    if (typeof value === 'string') {
        return key !== 'email' ? value.toUpperCase() : value;
    }
    return value;
});

console.log("Object with email in lowecase: ",obj);

const filter = Object.fromEntries(
    Object.entries(obj).filter(([key, value]) => key !== 'email')
);

const output = JSON.stringify(filter);

console.log("Object without email: ",output);
