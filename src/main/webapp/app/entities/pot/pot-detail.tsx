import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pot.reducer';
import { IPot } from 'app/shared/model/pot.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPotDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PotDetail extends React.Component<IPotDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { potEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Pot [<b>{potEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="potChips">Pot Chips</span>
            </dt>
            <dd>{potEntity.potChips}</dd>
            <dt>
              <span id="potPlayersSeatNumbers">Pot Players Seat Numbers</span>
            </dt>
            <dd>{potEntity.potPlayersSeatNumbers}</dd>
            <dt>Table Snapshot</dt>
            <dd>{potEntity.tableSnapshot ? potEntity.tableSnapshot.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pot" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pot/${potEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pot }: IRootState) => ({
  potEntity: pot.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PotDetail);
