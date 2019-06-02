import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TableStructure from './table-structure';
import TableSnapshot from './table-snapshot';
import Player from './player';
import Pot from './pot';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/table-structure`} component={TableStructure} />
      <ErrorBoundaryRoute path={`${match.url}/table-snapshot`} component={TableSnapshot} />
      <ErrorBoundaryRoute path={`${match.url}/player`} component={Player} />
      <ErrorBoundaryRoute path={`${match.url}/pot`} component={Pot} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
