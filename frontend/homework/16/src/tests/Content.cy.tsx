import React from 'react'
import { Content } from '../TodoList/Content'
import { Provider } from 'react-redux'
import { store } from '../Redux/stores/Store'

describe('Content-Component Test', () => {
  it('renders', () => {
    cy.viewport(1079,876)
    cy.mount(<Provider store={store}><Content /></Provider>)
    cy.get('.input').contains('Add Items').should('exist');
    cy.get('.input input[type="text"]').should('exist');
    cy.get('.input button.submit').should('exist');
    const item1 = 'Item 1';
    cy.get('.input input[type="text"]').type(item1);
    cy.get('.input button.submit').click();
    cy.contains(item1).should('exist');
    cy.get('.list .items input[type="checkbox"]').first().check();
    cy.get('.list .items .completed-text').should('exist');
    cy.get('.clearButton').click();
    cy.get('.list .items').should('not.exist');
    const item2 = 'Item 2';
    cy.get('.input input[type="text"]').type(item2);
    cy.get('.input button.submit').click();
    cy.contains(item2).should('exist');
    cy.get('.list .deleteButton').first().click();
    cy.get('.list .items').should('not.exist');

  });
})