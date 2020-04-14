import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class TopicUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('katoviApp.topic.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nameInput: ElementFinder = element(by.css('input#topic-name'));

  activeInput: ElementFinder = element(by.css('input#topic-active'));

  keyDeserializerInput: ElementFinder = element(by.css('input#topic-keyDeserializer'));

  valueDeserializerInput: ElementFinder = element(by.css('input#topic-valueDeserializer'));

  groupIdInput: ElementFinder = element(by.css('input#topic-groupId'));

  offsetInput: ElementFinder = element(by.css('input#topic-offset'));
}
