const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
        country: "Spain",
        city: "Barcelona",
    },
    careerInfo: {
        fcBarcelona: {
            appearances: 780,
            goals: {
                premierLeagueGoals: 590,
                championsLeagueGoals: 50,
            },
        },
    },
};

function Keys(obj) {
    const keys = [];

    function findKeys(obj, currentKey = '') {
        for (let key in obj) {
            if (typeof obj[key] === 'object' && obj[key] !== null) {
                findKeys(obj[key], key);
            } else {
                keys.push(key);
            }
        }
    }

    findKeys(obj);
    return keys;
}

const allKeys = Keys(player);
console.log("All Keys:", allKeys);


function Values(obj) {
    const val = [];

    function findVal(obj) {
        for (let key in obj) {
            if (typeof obj[key] === 'object' && obj[key] !== null) {
                findVal(obj[key]);
            } else {
                val.push(obj[key]);
            }
        }
    }

    findVal(obj);
    return val;
}

const allValues = Values(player);
console.log("All Values:", allValues);
