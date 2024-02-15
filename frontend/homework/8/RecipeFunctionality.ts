interface IRecipe {
    image: string; 
    name: string; 
    rating: number; 
    cuisine: string; 
    ingredients: string[]; 
    difficulty: string; 
    timeTaken: number; 
    calorieCount: number; 
}

class Recipe implements IRecipe {
    constructor(
        public image: string,
        public name: string,
        public rating: number,
        public cuisine: string,
        public ingredients: string[],
        public difficulty: string,
        public timeTaken: number,
        public calorieCount: number
    ) {}
}

interface IRawResponse {
    id: number;
    name: string;
    ingredients: string[];
    instructions: string[];
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    servings: number;
    difficulty: string;
    cuisine: string;
    caloriesPerServing: number;
    tags: string[];
    userId: number;
    image: string;
    rating: number;
    reviewCount: number;
    mealType: string[];
}

class RawResponse implements IRawResponse {
    constructor(
        public id: number,
        public name: string,
        public ingredients: string[],
        public instructions: string[],
        public prepTimeMinutes: number,
        public cookTimeMinutes: number,
        public servings: number,
        public difficulty: string,
        public cuisine: string,
        public caloriesPerServing: number,
        public tags: string[],
        public userId: number,
        public image: string,
        public rating: number,
        public reviewCount: number,
        public mealType: string[]
    ) {}
}

class RecipeFunctionality {
    private recipes: Recipe[] = [];
    public fetchUrl: string = 'https://dummyjson.com/recipes';
    public searchUrl: string = 'https://dummyjson.com/recipes/search?q=';

    async fetchRecipesFromAPI(): Promise<Recipe[]> {
        const response = await fetch(this.fetchUrl);
        const jsonData = await response.json();
        this.recipes = jsonData.recipes.map((recipe: any) => ({
            image: recipe.image,
            name: recipe.name,
            rating: recipe.rating,
            cuisine: recipe.cuisine,
            ingredients: recipe.ingredients,
            difficulty: recipe.difficulty,
            timeTaken: recipe.prepTimeMinutes + recipe.cookTimeMinutes,
            calorieCount: recipe.caloriesPerServing
        }));
        return this.recipes;
    }
    
    async searchRecipes(query: string): Promise<Recipe[]> {
        const searchUrl = this.searchUrl + encodeURIComponent(query);
        const response = await fetch(searchUrl);
        const jsonData = await response.json();
        const output: Recipe[] = jsonData.recipes.map((recipe: any) => {
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
        return output;
    }

    async printAllRecipes(): Promise<void> {
        try {
            console.log('All recipes:');
            this.recipes.forEach((recipe: Recipe) => {
                console.log(recipe);
            });
        } catch (error) {
            console.error('Error printing all recipes:', error);
        }
    }        
}

// HTML elements
const searchInput = document.getElementById('searchInput');
const recipesContainer = document.getElementById('recipesContainer');

// Function to display recipes
function displayRecipes(recipes) {
    recipesContainer.innerHTML = '';
    recipes.forEach(recipe => {
        const recipeBox = document.createElement('div');
        recipeBox.classList.add('recipe-box');
        recipeBox.innerHTML = `
            <h2>${recipe.name}</h2>
            <p>Rating: ${recipe.rating}</p>
            <p>Cuisine: ${recipe.cuisine}</p>
            <p>Difficulty: ${recipe.difficulty}</p>
            <p>Ingredients: ${recipe.ingredients.join(', ')}</p>
            <p>Time Taken: ${recipe.timeTaken}</p>
            <p>Calorie Count: ${recipe.calorieCount}</p>
            <img src="${recipe.image}" alt="${recipe.name}">
        `;
        recipesContainer.appendChild(recipeBox);
    });
}

// Function to filter recipes based on search query
function filterRecipes(query) {
    recipeFunctionality.searchRecipes(query)
        .then(recipes => displayRecipes(recipes))
        .catch(error => console.error('Error searching recipes:', error));
}

// Event listener for search input
searchInput.addEventListener('input', function() {
    filterRecipes(this.value);
});

// Create an instance of RecipeFunctionality
const recipeFunctionality = new RecipeFunctionality();

// Fetch recipes from API and display them initially
recipeFunctionality.fetchRecipesFromAPI()
    .then(recipes => displayRecipes(recipes))
    .catch(error => console.error('Error fetching recipes:', error));
