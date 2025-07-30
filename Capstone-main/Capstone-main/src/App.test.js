import { fireEvent, render, screen } from '@testing-library/react';
import BookingForm from './components/BookingForm';

const handleSubmit= jest.fn();
//TODO fix routing for testing. createMemoryHistory.
test('Renders', () => {
  render(<BookingForm/>);

  const headingElement = screen.getByText("Occassion");
  fireEvent.click(headingElement);

  //Test assumption
  expect(headingElement).toBeInTheDocument();

});

//TODO

//Add tests for Time api.
