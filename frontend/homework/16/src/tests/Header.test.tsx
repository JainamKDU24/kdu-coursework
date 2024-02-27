import { Provider } from "react-redux"
import { Header } from "../TodoList/Header"
import { store } from "../Redux/stores/Store"
import { fireEvent, render } from "@testing-library/react";
import { describe, expect, it } from "vitest";


describe('Header Test', () => {
    it('tests header', () => {

        const {getByText} = render(
            <Provider store={store}>
                <Header />
            </Provider>
        );

        expect(getByText('Item Lister')).not.toBeNull();
    });
    it('dispatches setSearchTerm action on input change', () => {
        const { getByPlaceholderText } = render(
          <Provider store={store}>
            <Header />
          </Provider>
        );
    
        const searchInput = getByPlaceholderText('Search Item...');
        fireEvent.change(searchInput, { target: { value: 'Test' } });
    
        // Test if setSearchTerm action is dispatched with the correct value
        expect(store.getState().todo.searchTerm).toBe('Test');
    });
})