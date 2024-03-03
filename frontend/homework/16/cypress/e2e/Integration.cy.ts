describe('Header and Content Components Integration Test', () => {
  beforeEach(() => {
    cy.visit('http://localhost:5173'); 
  });

  it('renders title and search input in Header', () => {
    cy.get('.Header').should('exist').and('be.visible');
    cy.get('.title').should('exist').and('be.visible').contains('Item Lister');
    cy.get('.search-bar').should('exist').and('be.visible');
  });

  it('adds, checks, deletes, and clears items in Content', () => {
    // Adding item
    cy.get('#item').type('Item 1');
    cy.get('.submit').click();
    cy.get('.items').should('have.length', 1).and('contain', 'Item 1');

    // Check item
    cy.get('.items input[type="checkbox"]').check();
    cy.get('.items span').should('have.class', 'completed-text');

    // Clear checked items
    cy.get('.items input[type="checkbox"]').check();
    cy.get('.clearButton').click();
    cy.get('.items').should('have.length', 0);
  });

  it('delete specific item and search item', () => {
    cy.get('#item').type('Item 1');
    cy.get('.submit').click();

    cy.get('#item').type('Item 2');
    cy.get('.submit').click();

    cy.get('#item').type('Item 3');
    cy.get('.submit').click();


    // Clicking the delete button for one of the items
    cy.get('.deleteButton').eq(0).click(); 
    cy.get('.items').should('have.length', 2); 

    // Searching for "Item 2"
    cy.get('#search').type('Item 2');
    cy.get('.items').should('have.length', 1).and('contain', 'Item 2');
  });
});
