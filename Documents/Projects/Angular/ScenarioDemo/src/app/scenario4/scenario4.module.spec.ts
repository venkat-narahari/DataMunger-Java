import { Scenario4Module } from './scenario4.module';

describe('Scenario4Module', () => {
  let scenario4Module: Scenario4Module;

  beforeEach(() => {
    scenario4Module = new Scenario4Module();
  });

  it('should create an instance', () => {
    expect(scenario4Module).toBeTruthy();
  });
});
