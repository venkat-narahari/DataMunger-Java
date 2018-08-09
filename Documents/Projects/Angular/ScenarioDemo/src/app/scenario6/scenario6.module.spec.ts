import { Scenario6Module } from './scenario6.module';

describe('Scenario6Module', () => {
  let scenario6Module: Scenario6Module;

  beforeEach(() => {
    scenario6Module = new Scenario6Module();
  });

  it('should create an instance', () => {
    expect(scenario6Module).toBeTruthy();
  });
});
