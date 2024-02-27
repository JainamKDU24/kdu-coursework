import { Provider } from 'react-redux';
import { Header } from '../TodoList/Header'
import { store } from '../Redux/stores/Store';

describe('Header-Component Test', () => {
  it('renders', () => {
    cy.viewport(1079, 876);
    cy.mount(
      <Provider store={store}>
        <Header />
      </Provider>
    );
    cy.get('.Header').contains('Item Lister').should('exist').and('be.visible');
    cy.get('.search-bar').should('exist').should('have.text','').and('be.visible');
    cy.get('.search-bar').type('test').and('be.visible');
  });
});