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

describe('Visits the browse page and clicks Buy', () => {

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

  it('Clicks buy 24 times (enough in stock)', () => {
    for (let i = 0; i < 23; i++) {
      cy.get('.card:nth-child(3) .buy').click();
    };
    cy.get('.card:nth-child(3) .buy')
      .should('not.have.class', 'cursor-not-allowed');
  });

  it('Clicks buy 25 times (not enough in stock)', () => {
    for (let i = 0; i < 24; i++) {
      cy.get('.card:nth-child(3) .buy').click();
    };
    cy.get('.card:nth-child(3) .buy')
      .should('have.class', 'cursor-not-allowed');
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

describe('Visits the (non-empty) cart page', () => {

  beforeEach(() => {
    cy.visit(URL);
    cy.contains('Browse').click();
    cy.contains('Buy').click();
    cy.contains('Buy').click();
    cy.contains('Shopping Cart').click();
  });

  it('Load the header\'s elements', () => {
    headerElements.forEach((label) => {
      cy.contains(label);
    });
  });

  it('Cart contains an item', () => {
    cy.contains('Total item count: 2');
  });

});

describe('Operates the shopping cart', () => {
  beforeEach(() => {
    cy.visit(URL);
    cy.contains('Browse').click();
    cy.get('.card:nth-child(3) .buy').click()
    cy.get('.card:nth-child(3) .buy').click()
    cy.contains('Shopping Cart').click();
  });

  it('Delete item', () => {
    cy.contains('🗑').click();
    cy.contains('empty');
  });

  it('Increase item\'s quantity in stock range', () => {
    cy.contains('+').click();
    cy.contains('Total item count: 3');
  });

  it('Increase item\'s quantity outside of stock range', () => {
    for (let i = 0; i < 22; i++) {
      cy.contains('+').click();
    }
    cy.contains('Total item count: 24');
    cy.contains('+').click();
    cy.contains('Total item count: 24');
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

  it('Buy item (success)', () => {
    cy.contains('Total item count: 2');
    cy.window().then(($win) => {
      cy.contains('Buy').click();
      cy.stub($win, 'prompt').returns('100000000');
      cy.stub($win, 'alert');
    });
    cy.contains('empty');
  });

});
