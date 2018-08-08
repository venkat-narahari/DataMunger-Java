import { Scenario2Module } from './scenario2.module';

describe('Scenario2Module', () => {
  let scenario2Module: Scenario2Module;

  beforeEach(() => {
    scenario2Module = new Scenario2Module();
  });

  it('should create an instance', () => {
    expect(scenario2Module).toBeTruthy();
  });
});
