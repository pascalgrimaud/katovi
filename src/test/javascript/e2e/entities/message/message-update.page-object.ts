import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class MessageUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('katoviApp.message.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  dateInput: ElementFinder = element(by.css('input#message-date'));

  valueInput: ElementFinder = element(by.css('input#message-value'));

  topicSelect = element(by.css('select#message-topic'));
}
