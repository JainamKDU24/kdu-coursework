// Part (B)
function modifying(str) {
    const replacements = {
        'i': '1',
        'o': '0',
        'a': '4',
        'e': '3',
        's': '5'
    };

    return str.trim().replace(/[aeios]/g, char => replacements[char] || char);
}

console.log(modifying("javascript is cool  "));
console.log(modifying("programming is fun"));
console.log(modifying("  become a coder"));