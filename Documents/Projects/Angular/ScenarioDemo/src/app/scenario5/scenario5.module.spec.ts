import { Scenario5Module } from './scenario5.module';

describe('Scenario5Module', () => {
  let scenario5Module: Scenario5Module;

  beforeEach(() => {
    scenario5Module = new Scenario5Module();
  });

  it('should create an instance', () => {
    expect(scenario5Module).toBeTruthy();
  });
});
