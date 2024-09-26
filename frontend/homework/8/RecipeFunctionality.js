var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var Recipe = /** @class */ (function () {
    function Recipe(image, name, rating, cuisine, ingredients, difficulty, timeTaken, calorieCount) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.difficulty = difficulty;
        this.timeTaken = timeTaken;
        this.calorieCount = calorieCount;
    }
    return Recipe;
}());
var RawResponse = /** @class */ (function () {
    function RawResponse(id, name, ingredients, instructions, prepTimeMinutes, cookTimeMinutes, servings, difficulty, cuisine, caloriesPerServing, tags, userId, image, rating, reviewCount, mealType) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTimeMinutes = prepTimeMinutes;
        this.cookTimeMinutes = cookTimeMinutes;
        this.servings = servings;
        this.difficulty = difficulty;
        this.cuisine = cuisine;
        this.caloriesPerServing = caloriesPerServing;
        this.tags = tags;
        this.userId = userId;
        this.image = image;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.mealType = mealType;
    }
    return RawResponse;
}());
var RecipeFunctionality = /** @class */ (function () {
    function RecipeFunctionality() {
        this.recipes = [];
        this.fetchUrl = 'https://dummyjson.com/recipes';
        this.searchUrl = 'https://dummyjson.com/recipes/search?q=';
    }
    RecipeFunctionality.prototype.fetchRecipesFromAPI = function () {
        return __awaiter(this, void 0, void 0, function () {
            var response, jsonData;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, fetch(this.fetchUrl)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.json()];
                    case 2:
                        jsonData = _a.sent();
                        this.recipes = jsonData.recipes.map(function (recipe) { return ({
                            image: recipe.image,
                            name: recipe.name,
                            rating: recipe.rating,
                            cuisine: recipe.cuisine,
                            ingredients: recipe.ingredients,
                            difficulty: recipe.difficulty,
                            timeTaken: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
                            calorieCount: recipe.caloriesPerServing
                        }); });
                        return [2 /*return*/, this.recipes];
                }
            });
        });
    };
    RecipeFunctionality.prototype.searchRecipes = function (query) {
        return __awaiter(this, void 0, void 0, function () {
            var searchUrl, response, jsonData, output;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        searchUrl = this.searchUrl + encodeURIComponent(query);
                        return [4 /*yield*/, fetch(searchUrl)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.json()];
                    case 2:
                        jsonData = _a.sent();
                        output = jsonData.recipes.map(function (recipe) {
                            return {
                                image: recipe.image,
                                name: recipe.name,
                                rating: recipe.rating,
                                cuisine: recipe.cuisine,
                                ingredients: recipe.ingredients,
                                difficulty: recipe.difficulty,
                                timeTaken: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
                                calorieCount: recipe.caloriesPerServing
                            };
                        });
                        console.log('Search results:', output);
                        return [2 /*return*/, output];
                }
            });
        });
    };
    RecipeFunctionality.prototype.printAllRecipes = function () {
        return __awaiter(this, void 0, void 0, function () {
            return __generator(this, function (_a) {
                try {
                    console.log('All recipes:');
                    this.recipes.forEach(function (recipe) {
                        console.log(recipe);
                    });
                }
                catch (error) {
                    console.error('Error printing all recipes:', error);
                }
                return [2 /*return*/];
            });
        });
    };
    return RecipeFunctionality;
}());
// HTML elements
var searchInput = document.getElementById('searchInput');
var recipesContainer = document.getElementById('recipesContainer');
// Function to display recipes
function displayRecipes(recipes) {
    recipesContainer.innerHTML = '';
    recipes.forEach(function (recipe) {
        var recipeBox = document.createElement('div');
        recipeBox.classList.add('recipe-box');
        recipeBox.innerHTML = "\n            <h2>".concat(recipe.name, "</h2>\n            <p>Rating: ").concat(recipe.rating, "</p>\n            <p>Cuisine: ").concat(recipe.cuisine, "</p>\n            <p>Difficulty: ").concat(recipe.difficulty, "</p>\n            <p>Ingredients: ").concat(recipe.ingredients.join(', '), "</p>\n            <p>Time Taken: ").concat(recipe.timeTaken, "</p>\n            <p>Calorie Count: ").concat(recipe.calorieCount, "</p>\n            <img src=\"").concat(recipe.image, "\" alt=\"").concat(recipe.name, "\">\n        ");
        recipesContainer.appendChild(recipeBox);
    });
}
// Function to filter recipes based on search query
function filterRecipes(query) {
    recipeFunctionality.searchRecipes(query)
        .then(function (recipes) { return displayRecipes(recipes); })
        .catch(function (error) { return console.error('Error searching recipes:', error); });
}
// Event listener for search input
searchInput.addEventListener('input', function () {
    filterRecipes(this.value);
});
// Create an instance of RecipeFunctionality
var recipeFunctionality = new RecipeFunctionality();
// Fetch recipes from API and display them initially
recipeFunctionality.fetchRecipesFromAPI()
    .then(function (recipes) { return displayRecipes(recipes); })
    .catch(function (error) { return console.error('Error fetching recipes:', error); });
