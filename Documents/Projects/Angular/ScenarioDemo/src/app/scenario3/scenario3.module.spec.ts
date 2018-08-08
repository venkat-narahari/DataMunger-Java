import { Scenario3Module } from './scenario3.module';

describe('Scenario3Module', () => {
  let scenario3Module: Scenario3Module;

  beforeEach(() => {
    scenario3Module = new Scenario3Module();
  });

  it('should create an instance', () => {
    expect(scenario3Module).toBeTruthy();
  });
});
