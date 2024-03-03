import { render, fireEvent, screen } from '@testing-library/react';
import { Provider } from 'react-redux';
import { store } from '../Redux/stores/Store';
import { Content } from '../TodoList/Content';

describe('Content Component', () => {
  test('initial page with no items', () => {
    render(
      <Provider store={store}>
        <Content />
      </Provider>
    );

    expect(screen.getByText('No items added')).to.exist;
  });

  test('renders title and input box and add button', () => {
    render(
      <Provider store={store}>
        <Content />
      </Provider>
    );

    expect(screen.getByText('Add Items')).to.exist;
    expect(screen.getByTestId('input-text')).to.exist;
    expect(screen.getByTestId('submit-button')).to.exist;
  });


  test('adds, checks, deletes, and clears items', () => {
    render(
      <Provider store={store}>
        <Content />
      </Provider>
    );

    // Adding item
    const input = screen.getByTestId('input-text');
    const submitButton = screen.getByTestId('submit-button');
    fireEvent.change(input, { target: { value: 'Test Item' } });
    fireEvent.click(submitButton);
    expect(screen.getByTestId('item-1')).to.exist;

    // Check item
    const checkbox = screen.getByTestId('item-checkbox-1');
    fireEvent.click(checkbox);
    expect(screen.getByTestId('item-1').classList.contains('completed')).to.exist;

    // Deleting item
    const deleteButton = screen.getByTestId('delete-button-1');
    fireEvent.click(deleteButton);
    expect(screen.queryByTestId('item-1')).not.to.exist;

    // Clear checked items
    fireEvent.click(checkbox);
    const clearButton = screen.getByTestId('clear-button');
    fireEvent.click(clearButton);
    expect(screen.queryByTestId('item-1')).not.to.exist;
  });
});
