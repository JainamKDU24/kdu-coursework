// Part (A)
const days = ["Sunday   ", "   Monday  ", "  Tuesday", "Wednesday  ", "  Thursday   ", "   Friday", "Saturday    "];
const result = days.map(Element => Element.trim().slice(0, 3).toUpperCase());
console.log(result);

