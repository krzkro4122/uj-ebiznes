const URL = 'http://localhost:9000/';

const headerElements = [
  'Goofy ahh™️',
  'Browse',
  'Shopping Cart 🛒'
];

describe('Home Page', () => {

  beforeEach(() => {
    cy.visit(URL)
  });

  it('Visits the home page', () => {
    cy.contains("There's no welcome page in order to optimize user retention");
  });

  it('Loads the header\'s elements', () => {
    headerElements.forEach((label) => {
      cy.contains(label);
    });
  });

});

describe('Browse Page', () => {

  beforeEach(() => {
    cy.visit(URL)
    cy.contains('Browse').click()
  });

  it('Visited the browse page', () => {
    cy.url().should('include', '/browse');
  });

  it('Loads the header\'s elements', () => {
    headerElements.forEach((label) => {
      cy.contains(label);
    });
  });

  it('Displays product cards', () => {
    cy.get('Card');
  });

});

describe('Cart Page', () => {

  beforeEach(() => {
    cy.visit(URL)
    cy.contains('Shopping Cart').click()
  });

  it('Visited the cart page', () => {
    cy.url().should('include', '/cart');
  });

  it('Loads the header\'s elements', () => {
    headerElements.forEach((label) => {
      cy.contains(label);
    });
  });

  it('Cart is empty', () => {
    cy.get('Card');
  });

});