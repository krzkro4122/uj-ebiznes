const URL = 'http://localhost:9000/';

const headerElements = [
  'Goofy ahh™️',
  'Browse',
  'Shopping Cart 🛒'
];

const productNames = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'].map((letter) => 'Product ' + letter);

describe('Visits the home page', () => {

  beforeEach(() => {
    cy.visit(URL)
  });

  it('Read the home page\'s banner', () => {
    cy.contains("There's no welcome page in order to optimize user retention");
  });

  it('Load the header\'s elements', () => {
    headerElements.forEach((label) => {
      cy.contains(label);
    });
  });

});

describe('Visits the browse page', () => {

  beforeEach(() => {
    cy.visit(URL)
    cy.contains('Browse').click()
  });

  it('Visit the browse page', () => {
    cy.url().should('include', '/browse');
  });

  it('Load the header\'s elements', () => {
    headerElements.forEach((label) => {
      cy.contains(label);
    });
  });

  it('Display product cards', () => {
    productNames.forEach((product) => cy.contains(product));
  });

});

describe('Visits the (empty) cart page', () => {

  beforeEach(() => {
    cy.visit(URL)
    cy.contains('Shopping Cart').click()
  });

  it('Visit the cart page', () => {
    cy.url().should('include', '/cart');
  });

  it('Load the header\'s elements', () => {
    headerElements.forEach((label) => {
      cy.contains(label);
    });
  });

  it('Cart is empty', () => {
    cy.contains('Your cart is empty 🚮');
  });

});

describe('Visits the (empty) cart page', () => {

  beforeEach(() => {
    cy.visit(URL)
    cy.contains('Shopping Cart').click()
  });

  it('Visit the cart page', () => {
    cy.url().should('include', '/cart');
  });

  it('Load the header\'s elements', () => {
    headerElements.forEach((label) => {
      cy.contains(label);
    });
  });

  it('Cart is empty', () => {
    cy.contains('Your cart is empty 🚮');
  });

});

describe('Operates the shopping cart', () => {
  beforeEach(() => {
    cy.visit(URL);
    cy.contains('Browse').click();
    cy.contains('Buy').click();
    cy.contains('Buy').click();
    cy.contains('Shopping Cart').click();
  });

  it('Delete item', () => {
    cy.contains('🗑').click();
    cy.contains('empty');
  });

  it('Increase item\'s quantity', () => {
    cy.contains('+').click();
    cy.contains('Total item count: 3');
  });

  it('Decrease item\'s quantity', () => {
    cy.contains('-').click();
    cy.contains('Total item count: 1');
  });

  it('Buy item (not enough money)', () => {
    cy.contains('Total item count: 2');
    cy.window().then(($win) => {
      cy.contains('Buy').click();
      cy.stub($win, 'prompt').returns('0');
      cy.stub($win, 'alert');
    });
    cy.contains('Total item count: 2');
  });

  it('Buy item (enough money)', () => {
    cy.contains('Total item count: 2');
    cy.window().then(($win) => {
      cy.contains('Buy').click();
      cy.stub($win, 'prompt').returns('30');
      cy.stub($win, 'alert');
    });
    cy.contains('empty');
  });
});
